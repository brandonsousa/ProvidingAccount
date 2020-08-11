'use strict'

class AuthController {
    async login({ request, response, auth }) {
        const { code, password } = request.all()
        const token = await auth.attempt(code, password)

        if (token) {
            return response.status(200).send({
                login: true,
                token: token.token
            })
        }

        return response.status(401).send({
            login: false,
            token: 'Invalid credentials, try again'
        })
    }

    async logout({ response, auth }) {
        await auth.logout()
        return response.status(200).send({
            logout: true
        })
    }
}

module.exports = AuthController
