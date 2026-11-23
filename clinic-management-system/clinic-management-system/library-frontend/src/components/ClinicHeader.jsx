export default function ClinicHeader({ title = 'Clinic Management', subtitle = 'Patient care workspace', user = 'Administrator' }) {
  return <header className="clinic-header"><div><strong>{title}</strong><small>{subtitle}</small></div><div className="clinic-header-user"><span className="clinic-header-avatar">AD</span><span>{user}</span></div></header>;
}
