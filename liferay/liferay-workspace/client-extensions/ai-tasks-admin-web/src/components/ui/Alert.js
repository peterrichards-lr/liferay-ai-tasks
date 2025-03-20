/**
 * @author Louis-Guillaume Durand
 */

const Alert = ({ type, lead, message }) => (
  <div className={'alert alert-' + type}>
    {lead && <strong className="lead">{lead}:</strong>}
    {message}
  </div>
);

export default Alert;
