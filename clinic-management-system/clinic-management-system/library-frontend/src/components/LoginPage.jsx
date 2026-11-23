import { useState } from 'react';

export default function LoginPage({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  function submit(e) { e.preventDefault(); onLogin?.({ username, password }); }
  return <main className="login-page"><form className="login-card" onSubmit={submit}><div className="login-mark">+</div><h1>Clinic Management</h1><p>Sign in to access your clinic workspace.</p><label>Username<input value={username} onChange={e => setUsername(e.target.value)} required /></label><label>Password<input type="password" value={password} onChange={e => setPassword(e.target.value)} required /></label><button className="btn-primary" type="submit">Sign in</button></form></main>;
}
