import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api'
import Layout from '../components/Layout'

const blank={organizationType:'ENTERPRISE',incidentDetails:'',reportedDateTime:'',priority:'MEDIUM',status:'OPEN'}

export default function IncidentForm(){
  const [form,setForm]=useState(blank); const [info,setInfo]=useState(null); const [error,setError]=useState(''); const [saving,setSaving]=useState(false)
  const {incidentId}=useParams(); const navigate=useNavigate(); const editing=Boolean(incidentId)
  const set=(k,v)=>setForm(f=>({...f,[k]:v}))
  useEffect(()=>{(async()=>{try{const u=await api.get('/users/me');setInfo(u.data);if(editing){const r=await api.get(`/incidents/${incidentId}`);setForm({...r.data,reportedDateTime:r.data.reportedDateTime.slice(0,16)})}else set('reportedDateTime',new Date().toISOString().slice(0,16))}catch(err){setError(err.response?.data?.message||'Unable to load')}})()},[incidentId])
  const closed=form.status==='CLOSED'
  async function submit(e){e.preventDefault();if(closed&&editing)return;setSaving(true);setError('');try{if(editing)await api.put(`/incidents/${incidentId}`,form);else await api.post('/incidents',form);navigate('/dashboard')}catch(err){setError(err.response?.data?.message||'Save failed')}finally{setSaving(false)}}
  return <Layout title={editing?'Incident Details':'Create Incident'}><div className="card">
    <h2>{editing?'Incident: '+incidentId:'Create Incident'}</h2>{error&&<div className="error">{error}</div>}
    {info&&<div className="reporter"><b>Reporter:</b> {info.username} &nbsp; {info.email} &nbsp; {info.phone}</div>}
    <form onSubmit={submit}><div className="grid2">
      <label>Enterprise / Government<select value={form.organizationType} onChange={e=>set('organizationType',e.target.value)} disabled={closed}><option value="ENTERPRISE">Enterprise</option><option value="GOVERNMENT">Government</option></select></label>
      <label>Reported Date & Time<input type="datetime-local" value={form.reportedDateTime} onChange={e=>set('reportedDateTime',e.target.value)} disabled={closed} required/></label>
      <label>Priority<select value={form.priority} onChange={e=>set('priority',e.target.value)} disabled={closed}><option>HIGH</option><option>MEDIUM</option><option>LOW</option></select></label>
      <label>Status<select value={form.status} onChange={e=>set('status',e.target.value)} disabled={closed}><option value="OPEN">Open</option><option value="IN_PROGRESS">In progress</option><option value="CLOSED">Closed</option></select></label>
    </div>
    <label>Incident Details<textarea rows="8" value={form.incidentDetails} onChange={e=>set('incidentDetails',e.target.value)} disabled={closed} required/></label>
    {closed&&<div className="warning">This incident is CLOSED and cannot be edited.</div>}
    {!closed&&<button className="primary" disabled={saving}>{saving?'Saving...':editing?'Update Incident':'Create Incident'}</button>}
    <button type="button" onClick={()=>navigate('/dashboard')}>Back</button>
    </form></div></Layout>
}
