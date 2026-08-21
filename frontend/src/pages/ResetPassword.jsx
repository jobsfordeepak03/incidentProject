import { useState } from 'react'
import { useSearchParams, useNavigate } from 'react-router-dom'
import api from '../api'

export default function ResetPassword() {
  const [params]=useSearchParams(); const [token,setToken]=useState(params.get('token')||''); const [password,setPassword]=useState(''); const [message,setMessage]=useState(''); const [error,setError]=useState(''); const navigate=useNavigate()
  async function submit(e){e.preventDefault();try{await api.post('/auth/reset-password',{token,newPassword:password});setMessage('Password reset successful');setTimeout(()=>navigate('/login'),1000)}catch(err){setError(err.response?.data?.message||'Reset failed')}}
  return <div className="authPage"><form className="card auth" onSubmit={submit}><h2>Reset Password</h2>{error&&<div className="error">{error}</div>}{message&&<div className="success">{message}</div>}
    <label>Reset Token<input value={token} onChange={e=>setToken(e.target.value)} required/></label>
    <label>New Password<input type="password" value={password} onChange={e=>setPassword(e.target.value)} minLength="8" required/></label>
    <button className="primary">Reset Password</button></form></div>
}
