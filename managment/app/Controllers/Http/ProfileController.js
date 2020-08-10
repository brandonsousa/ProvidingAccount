'use strict'

const User = use('App/Models/User')

class ProfileController {

    async index({ view, auth }) {
        return view.render('profile.index')
    }

    async update({ request, response, auth }) {

        const data = request.except(['_method'])

        const user = await User.find(auth.user.id)

        if (data.password == null) {
            user.merge({
                username: data.username,
                email: data.email,
            })
        } else {
            user.merge(data)
        }

        await user.save()

        return response.redirect('/profile')

    }

}

module.exports = ProfileController
