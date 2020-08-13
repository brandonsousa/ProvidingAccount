'use strict'

const Receipt = use('App/Models/Receipt')
const User = use('App/Models/User')
class ReportController {

    async index({ view }) {
        return view.render('report.index')
    }

    async store({ request, response, session, view }) {
        const { code } = request.all()

        const user = await User.findBy('code', code)

        if (user) {
            const my_receipts = await Receipt.query().where('user_id', user.id).fetch()
            if (my_receipts) {
                return view.render('report.template', {
                    receipts: my_receipts.toJSON()
                })
            }
            session.flash({ error: 'No receipts to you' })
            return response.redirect('/report')
        }

        session.flash({ error: 'User not found' })
        return response.redirect('/report')
    }

}

module.exports = ReportController
