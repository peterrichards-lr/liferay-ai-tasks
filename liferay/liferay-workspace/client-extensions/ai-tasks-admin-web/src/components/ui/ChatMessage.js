/**
 * @author Louis-Guillaume Durand
 */
import { useTooltip } from '../../contexts/TooltipContext';
import useClickOutside from '../../hooks/useClickOutside';
import Icon from './Icon';

const ChatMessage = ({ role, debug, children }) => {
  const { isTooltipOpen, setIsTooltipOpen, setTooltipPosition, setTooltipContent } = useTooltip();

  const handleClickOutside = () => {
    setIsTooltipOpen(false);
  };

  const buttonRef = useClickOutside(handleClickOutside);

  const handleDebugDisplay = (e) => {
    e.preventDefault();
    e.stopPropagation();
    setTooltipPosition({
      top: e.target.getBoundingClientRect().top - 100,
      left: e.target.getBoundingClientRect().left,
      right: e.target.getBoundingClientRect().right,
      bottom: e.target.getBoundingClientRect().bottom,
    });
    setTooltipContent(
      <div className={'d-flex flex-column'}>
        <div>Execution: {debug.executionTime}</div>
        <div>Input Tokens: {debug.inputTokenCount}</div>
        <div>Output Tokens: {debug.outputTokenCount}</div>
        <div>Total Tokens: {debug.totalTokenCount}</div>
      </div>,
    );
    setIsTooltipOpen(!isTooltipOpen);
  };

  return (
    <>
      <div
        className={`chat-preview-message d-flex mb-2 ${
          role === 'USER' ? 'justify-content-end' : 'justify-content-start'
        }`}
      >
        <div className={`message ${role === 'USER' ? 'user-message' : 'system-message'}`}>
          {children}
        </div>
      </div>
      {debug && (
        <>
          <button
            ref={buttonRef}
            className={'btn btn-default btn-sm px-2 py-0'}
            style={{ width: '2rem' }}
            onClick={handleDebugDisplay}
          >
            <Icon name={'info-circle-open'} />
          </button>
        </>
      )}
    </>
  );
};

export default ChatMessage;
