'use strict'

const Receipt = use('App/Models/Receipt')
const User = use('App/Models/User')

class ProfileController {

    async index({ view, auth }) {
        const receipts = await Receipt.findBy('user_id', auth.user.id)
        return view.render('profile.index', {
            receipts: receipts
        })
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
