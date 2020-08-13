'use strict'

const Database = use('Database')

const User = use('App/Models/User')
class ReportController {

    async index({ view }) {
        return view.render('report.index')
    }

    async store({ request, response, session, view }) {
        const { code } = request.all()

        const user = await User.findBy('code', code)

        if (user) {
            await user.load('receipts')
            const food = await Database.raw('SELECT ROUND(SUM(price),2) AS total FROM receipts WHERE category = "Food" AND user_id = ?', user.id)
            const accommodation = await Database.raw('SELECT ROUND(SUM(price),2) AS total FROM receipts WHERE category = "Accommodation" AND user_id = ?', user.id)
            const transport = await Database.raw('SELECT ROUND(SUM(price),2) AS total FROM receipts WHERE category = "Transport" AND user_id = ?', user.id)
            const others = await Database.raw('SELECT ROUND(SUM(price),2) AS total FROM receipts WHERE category = "Others"  AND user_id = ?', user.id)
            return view.render('report.template', {
                user: user.toJSON(),
                food: food[0],
                accommodation: accommodation[0],
                transport: transport[0],
                others: others[0]
            })
        }

        session.flash({ error: 'User not found' })
        return response.redirect('/report')
    }

}

module.exports = ReportController
