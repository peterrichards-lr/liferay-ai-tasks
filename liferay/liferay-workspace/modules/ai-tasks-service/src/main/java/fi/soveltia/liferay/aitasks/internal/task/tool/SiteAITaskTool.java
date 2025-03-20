package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(property = "ai.task.tool.name=site", service = AITaskTool.class)
public class SiteAITaskTool implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject configurationJSONObject) {
		return new Executor();
	}

	public class Executor {

		@Tool("Creates a new site")
		public Group createSite(
				@P("The description for the new site") String description,
				@P("The name for the new site") String name,
				@P("The name of the site template to use") String siteTemplate)
			throws PortalException {

			try {
				Group group = GroupLocalServiceUtil.addGroup(
						PrincipalThreadLocal.getUserId(),
						GroupConstants.DEFAULT_PARENT_GROUP_ID,
						Group.class.getName(),
						0,
						GroupConstants.DEFAULT_LIVE_GROUP_ID,
						LocalizedMapUtil.getLocalizedMap(name),
						LocalizedMapUtil.getLocalizedMap(description),
						GroupConstants.TYPE_SITE_OPEN,
						true,
						GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, // Membership restriction
						null,
						true,
						true,
						ServiceContextThreadLocal.getServiceContext());

				_initializeSite(group, siteTemplate);

				return group;
			}
			catch (PortalException portalException) {
				log.error(portalException);

				throw portalException;
			}
		}

		private void _initializeSite(Group group, String siteTemplate)
			throws InitializationException {

			String initializerName = siteInitializers.get(
				StringUtil.toLowerCase(siteTemplate));

			if (Validator.isBlank(initializerName)) {
				return;
			}

			SiteInitializer siteInitializer =
				siteInitializerRegistry.getSiteInitializer(initializerName);

			siteInitializer.initialize(group.getGroupId());
		}

	}

	protected static final Log log = LogFactoryUtil.getLog(
		SiteAITaskTool.class);
	protected static final Map<String, String> siteInitializers =
		new HashMap<String, String>() {
			{
				put("masterclass", "com.liferay.site.initializer.masterclass");
				put("minium", "minium-initializer");
				put("speedwell", "speedwell-initializer");
				put("welcome", "com.liferay.site.initializer.welcome");
			}
		};

	@Reference
	protected SiteInitializerRegistry siteInitializerRegistry;

}