import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import api from '../api'


export default function Register() {
  const [form, setForm] = useState({
    accountType: 'INDIVIDUAL',
    firstName: '', lastName: '', email: '', address: '',
    country: '', state: '', city: '', pincode: '',
    mobileCode: '+91', phone: '', telephone: '',
    password: '', confirmPassword: ''
  })
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const set = (key, value) => setForm(f => ({ ...f, [key]: value }))

  async function lookupPin(value) {
    set('pincode', value)
    if (/^\d{6}$/.test(value)) {
      try {
        const { data } = await api.get(`/users/pincode/${value}`)
        setForm(f => ({
          ...f,
          pincode: value,
          city: data.city,
          country: data.country,
          state: data.state || f.state
        }))
      } catch (_) {}
    }
  }

  async function submit(e) {
    e.preventDefault()
    setError('')
    if (form.password !== form.confirmPassword) {
      setError('Confirm password should be same as password')
      return
    }

    try {
      await api.post('/auth/register', {
        username: `${form.firstName} ${form.lastName}`.trim(),
        email: form.email,
        phone: `${form.mobileCode}${form.phone}`,
        address: form.address,
        pincode: form.pincode,
        city: form.city,
        country: form.country,
        password: form.password
      })
      navigate('/login')
    } catch (err) {
      setError(err.response?.data?.message ||
        Object.values(err.response?.data || {}).join(', ') ||
        'Registration failed')
    }
  }

  return (
    <div className="userdata-page">
      <div className="userdata-topbar"></div>

      <section className="userdata-panel">
        <div className="userdata-tabs">
          <Link to="/login" className="userdata-tab">LOGIN</Link>
          <div className="userdata-tab active">SIGNUP</div>
        </div>

        <form onSubmit={submit}>
          <div className="account-type">
            <span>Individual/Enterprise/Government *</span>
            <label><input type="radio" checked={form.accountType === 'INDIVIDUAL'} onChange={() => set('accountType','INDIVIDUAL')} /> Individual</label>
            <label><input type="radio" checked={form.accountType === 'ENTERPRISE'} onChange={() => set('accountType','ENTERPRISE')} /> Enterprise</label>
            <label><input type="radio" checked={form.accountType === 'GOVERNMENT'} onChange={() => set('accountType','GOVERNMENT')} /> Government</label>
          </div>

          {error && <div className="userdata-error">{error}</div>}

          <div className="userdata-grid">
            <label>First Name *<input placeholder="First Name" value={form.firstName} onChange={e=>set('firstName',e.target.value)} required /></label>
            <label>Last Name<input placeholder="Last Name" value={form.lastName} onChange={e=>set('lastName',e.target.value)} /></label>

            <label className="full">Email *<input type="email" placeholder="Email" value={form.email} onChange={e=>set('email',e.target.value)} required /></label>
            <label className="full">Address *<input placeholder="Address" value={form.address} onChange={e=>set('address',e.target.value)} required /></label>

            <label>Country *<input placeholder="Country" value={form.country} onChange={e=>set('country',e.target.value)} required /></label>
            <label>State<input placeholder="State" value={form.state} onChange={e=>set('state',e.target.value)} /></label>

            <label>City *<input placeholder="City" value={form.city} onChange={e=>set('city',e.target.value)} required /></label>
            <label>Pincode *<input placeholder="Pincode" maxLength="6" value={form.pincode} onChange={e=>lookupPin(e.target.value)} required /></label>

            <label className="full">Mobile Number *
              <div className="mobile-row">
                <select value={form.mobileCode} onChange={e=>set('mobileCode',e.target.value)}>
                  <option>+91</option><option>+1</option><option>+44</option>
                </select>
                <input placeholder="Mobile number" value={form.phone} onChange={e=>set('phone',e.target.value)} required />
              </div>
            </label>

            <label>Telephone<input placeholder="Telephone" value={form.telephone} onChange={e=>set('telephone',e.target.value)} /></label>
            <div></div>

            <label className="full">Password *
              <input type="password" placeholder="Password" minLength="8" value={form.password} onChange={e=>set('password',e.target.value)} required />
              <small>Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters.</small>
            </label>

            <label className="full">Confirm Password *
              <input type="password" placeholder="Confirm Password" value={form.confirmPassword} onChange={e=>set('confirmPassword',e.target.value)} required />
              <small>Confirm password should be same as password</small>
            </label>
          </div>

          <button className="userdata-submit" type="submit">SIGNUP</button>
        </form>
      </section>
    </div>
  )
}
