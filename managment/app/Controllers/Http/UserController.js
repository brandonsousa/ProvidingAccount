'use strict'

/** @typedef {import('@adonisjs/lucid/src/Lucid/Model')} */
const User = use('App/Models/User')

class UserController {

    async index({ view }) {
        const users = await User.all()
        return view.render('user.index', { users: users.toJSON() })
    }

    async create({ view }) {
        return view.render('user.create')
    }

    async store({ request, view, response, auth }) {
        const data = request.all()
        if (auth.user.admin == 1) {
            const user = await User.create({
                'user_id': auth.user.id,
                ...data
            })

            if (user) {
                session.flash({ success: 'User created' })
                return response.redirect('/user/create')
            }
            session.flash({ error: 'User not created' })
            return response.redirect('/user/create')
        }
        session.flash({ error: "You can't permission" })
        return response.redirect('/user/create')
    }
}

module.exports = UserController
