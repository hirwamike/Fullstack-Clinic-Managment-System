import Office from './Office';

export default function OfficeList({ offices = [], onEdit, onDelete }) {
  if (!offices.length) return <div className="empty-state"><b>No offices registered</b><p>Add an office to see it listed here.</p></div>;
  return <div className="office-list">{offices.map(office => <Office key={office.id} office={office} onEdit={onEdit} onDelete={onDelete} />)}</div>;
}
