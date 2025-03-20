/**
 * @author Louis-Guillaume Durand
 */

const Icon = ({ name, className }) => {
  return (
    <svg
      aria-hidden="true"
      className={'lexicon-icon lexicon-icon-' + name + ' ' + className}
      focusable="false"
    >
      <use href={'/o/dialect-theme/images/clay/icons.svg#' + name}></use>
    </svg>
  );
};

export default Icon;
