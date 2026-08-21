import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api'
import Layout from '../components/Layout'

export default function Dashboard() {
  const [incidents,setIncidents]=useState([]); const [search,setSearch]=useState(''); const [error,setError]=useState('')
  async function load(){try{const {data}=await api.get('/incidents');setIncidents(data)}catch(err){setError(err.response?.data?.message||'Unable to load incidents')}}
  useEffect(()=>{load()},[])
  async function find(){if(!search.trim()) return load();try{const {data}=await api.get('/incidents/search',{params:{incidentId:search.trim()}});setIncidents([data]);setError('')}catch(err){setIncidents([]);setError(err.response?.data?.message||'Incident not found')}}
  async function remove(id){if(!confirm('Delete this incident?'))return;try{await api.delete(`/incidents/${id}`);load()}catch(err){setError(err.response?.data?.message||'Delete failed')}}
  return <Layout><div className="toolbar"><div><h2>My Incidents</h2><p>Welcome, {localStorage.getItem('username')}</p></div><Link className="primary button" to="/incidents/new">+ Create Incident</Link></div>
    <div className="search"><input placeholder="Search by Incident ID e.g. RMG123452026" value={search} onChange={e=>setSearch(e.target.value)}/><button onClick={find}>Search</button><button onClick={load}>All</button></div>
    {error&&<div className="error">{error}</div>}
    <div className="tableWrap"><table><thead><tr><th>Incident ID</th><th>Organization</th><th>Priority</th><th>Status</th><th>Reported</th><th>Actions</th></tr></thead><tbody>
      {incidents.map(i=><tr key={i.incidentId}><td><strong>{i.incidentId}</strong></td><td>{i.organizationType}</td><td><span className={`badge ${i.priority.toLowerCase()}`}>{i.priority}</span></td><td><span className={`badge ${i.status.toLowerCase()}`}>{i.status}</span></td><td>{new Date(i.reportedDateTime).toLocaleString()}</td><td><Link to={`/incidents/${i.incidentId}/edit`}>View / Edit</Link>{i.status!=='CLOSED'&&<button className="dangerLink" onClick={()=>remove(i.incidentId)}>Delete</button>}</td></tr>)}
    </tbody></table></div>
  </Layout>
}
