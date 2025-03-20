/**
 * @author Louis-Guillaume Durand
 */

const EmptyState = ({ image, title, description, button }) => {
  const images = {
    empty: '/o/admin-theme/images/states/empty_state.svg',
    search: '/o/admin-theme/images/states/search_state.svg',
    success: '/o/admin-theme/images/states/success_state.svg',
  };

  return (
    <div className="c-empty-state c-empty-state-animation">
      <div className="c-empty-state-image">
        <div className="c-empty-state-aspect-ratio">
          <img
            alt="empty-state-image"
            className="aspect-ratio-item aspect-ratio-item-fluid"
            src={images[image] !== undefined ? images[image] : images['empty']}
          />
        </div>
      </div>
      <div className="c-empty-state-title">
        <span className="text-truncate-inline">
          <span className="text-truncate">{title || 'No results found'}</span>
        </span>
      </div>
      <div className="c-empty-state-text">{description || 'Sorry, there are no results found'}</div>
      {button && <div className="c-empty-state-footer">{button}</div>}
    </div>
  );
};

export default EmptyState;
