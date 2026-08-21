import { Link, useNavigate } from 'react-router-dom'
export default function Layout({ children, title = 'Incident Management' }) {
  const navigate = useNavigate()
  const logout = () => { localStorage.clear(); navigate('/login') }
  return <div className="app">
    <header>
      <div><strong>{title}</strong></div>
      <nav>
        <Link to="/dashboard">Dashboard</Link>
        <Link to="/incidents/new">Create Incident</Link>
        <button className="linkButton" onClick={logout}>Logout</button>
      </nav>
    </header>
    <main>{children}</main>
  </div>
}
