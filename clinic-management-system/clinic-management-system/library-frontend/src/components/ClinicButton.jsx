export default function ClinicButton({ children, onClick, type = 'button', variant = 'primary', disabled = false }) {
  return <button type={type} className={`clinic-button clinic-button-${variant}`} onClick={onClick} disabled={disabled}>{children}</button>;
}
