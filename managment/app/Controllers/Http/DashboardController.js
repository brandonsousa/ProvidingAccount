'use strict'

class DashboardController {
    async index({ request, view, response, auth }){
        return view.render('dashboard.index')
    }
}

module.exports = DashboardController
