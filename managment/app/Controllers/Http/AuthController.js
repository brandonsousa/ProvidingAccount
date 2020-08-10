'use strict'
const User = use('App/Models/User')
class AuthController {
    async index({ view, auth }) {
        if (auth.user != null) {
            console.log('logado')
        }
        return view.render('auth.login')
    }

    async login({ request, response, session, auth }) {
        const { code, password } = request.all()
        await auth.attempt(code, password)
        if (auth.user != null) {
            return response.redirect('/dashboard')
        }
        session.flash({ error: 'Invalid Login, try again' })
        response.redirect('/')
    }
}

module.exports = AuthController
