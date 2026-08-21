import { useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api'

export default function ForgotPassword() {
  const [email,setEmail]=useState(''); const [token,setToken]=useState(''); const [error,setError]=useState('')
  async function submit(e){e.preventDefault();setError('');try{const {data}=await api.post('/auth/forgot-password',{email});setToken(data.resetToken)}catch(err){setError(err.response?.data?.message||'Unable to create reset token')}}
  return <div className="authPage"><form className="card auth" onSubmit={submit}>
    <h2>Forgot Password</h2>{error&&<div className="error">{error}</div>}
    <label>Email<input type="email" value={email} onChange={e=>setEmail(e.target.value)} required/></label>
    <button className="primary">Generate Reset Token</button>
    {token&&<div className="success">Demo reset token:<br/><code>{token}</code><br/><Link to={`/reset-password?token=${token}`}>Continue to Reset Password</Link></div>}
    <Link to="/login">Back to Login</Link>
  </form></div>
}
