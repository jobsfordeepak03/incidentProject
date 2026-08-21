import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import api from '../api'

export default function Register() {
  const [form, setForm] = useState({username:'',email:'',phone:'',address:'',pincode:'',city:'',country:'',password:''})
  const [message, setMessage] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()
  const set = (k,v) => setForm({...form,[k]:v})

  async function lookupPin(v) {
    set('pincode', v)
    if (/^\d{6}$/.test(v)) {
      try { const {data}=await api.get(`/users/pincode/${v}`); setForm(f=>({...f,city:data.city,country:data.country})) }
      catch {}
    }
  }

  async function submit(e) {
    e.preventDefault(); setError(''); setMessage('')
    try { await api.post('/auth/register', form); setMessage('Registration successful. Redirecting to login...'); setTimeout(()=>navigate('/login'),1000) }
    catch(err) { setError(err.response?.data?.message || Object.values(err.response?.data || {}).join(', ') || 'Registration failed') }
  }

  return <div className="authPage"><form className="card auth wide" onSubmit={submit}>
    <h2>User Registration</h2>{error&&<div className="error">{error}</div>}{message&&<div className="success">{message}</div>}
    <div className="grid2">
      <label>User Name<input value={form.username} onChange={e=>set('username',e.target.value)} required/></label>
      <label>Email<input type="email" value={form.email} onChange={e=>set('email',e.target.value)} required/></label>
      <label>Phone<input value={form.phone} onChange={e=>set('phone',e.target.value)} required/></label>
      <label>PIN Code<input value={form.pincode} onChange={e=>lookupPin(e.target.value)} maxLength="6" required/></label>
      <label>City<input value={form.city} readOnly required/></label>
      <label>Country<input value={form.country} readOnly required/></label>
    </div>
    <label>Address<textarea value={form.address} onChange={e=>set('address',e.target.value)} required/></label>
    <label>Password<input type="password" value={form.password} onChange={e=>set('password',e.target.value)} minLength="8" required/></label>
    <button className="primary">Register</button>
    <p>Already registered? <Link to="/login">Login</Link></p>
  </form></div>
}
