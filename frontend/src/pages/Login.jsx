import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import api from '../api'

export default function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()

  async function submit(e) {
    e.preventDefault(); setError('')
    try {
      const { data } = await api.post('/auth/login', { email, password })
      localStorage.setItem('token', data.token)
      localStorage.setItem('username', data.username)
      navigate('/dashboard')
    } catch (err) { setError(err.response?.data?.message || 'Invalid login') }
  }

  return <div className="authPage"><form className="card auth" onSubmit={submit}>
    <h1>Incident Management</h1><h2>Login</h2>
    {error && <div className="error">{error}</div>}
    <label>Email<input type="email" value={email} onChange={e=>setEmail(e.target.value)} required /></label>
    <label>Password<input type="password" value={password} onChange={e=>setPassword(e.target.value)} required /></label>
    <button className="primary">Login</button>
    <div className="actions"><Link to="/forgot-password">Forgot Password?</Link><Link to="/register">Create Account</Link></div>
  </form></div>
}
