package fi.soveltia.liferay.aitasks.internal.application.list;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;

import fi.soveltia.liferay.aitasks.constants.AITasksPanelCategoryKeys;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = {
		"panel.category.key=" + PanelCategoryKeys.APPLICATIONS_MENU_APPLICATIONS,
		"panel.category.order:Integer=500"
	},
	service = PanelCategory.class
)
public class AITasksPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return AITasksPanelCategoryKeys.CONTROL_PANEL_AI_TASKS;
	}

	@Override
	public String getLabel(Locale locale) {
		return "AI Tasks";
	}

}