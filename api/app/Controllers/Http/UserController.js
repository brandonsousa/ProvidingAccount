'use strict'

const User = use('App/Models/User')
class UserController {
    async index({ auth }) {
        const user = await User.find(auth.user.id)
        if (user) {
            return response.status(200).send(user)
        }
        return response.status(404).send('error, user not found')
    }
}

module.exports = UserController
