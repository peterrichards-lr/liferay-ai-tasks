/**
 * @author Louis-Guillaume Durand
 */

const Toast = {
  open: (type, title, message) => {
    if (typeof window.Liferay !== undefined) {
      window.Liferay.Util.openToast({
        title: title,
        message: message,
        type: type,
      });
    }
  },
};

export default Toast;
