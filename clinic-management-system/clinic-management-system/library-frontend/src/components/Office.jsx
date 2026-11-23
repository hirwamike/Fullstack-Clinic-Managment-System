export default function Office({ office, onEdit, onDelete }) {
  return <article className="office-card"><div><strong>{office?.name || 'Office'}</strong><small>Room {office?.officeNumber ?? '—'}</small></div><div className="office-card-actions"><button onClick={() => onEdit?.(office)}>Edit</button><button onClick={() => onDelete?.(office)}>Delete</button></div></article>;
}
