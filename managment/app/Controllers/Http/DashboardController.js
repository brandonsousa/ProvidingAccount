'use strict'

const Database = use('Database')

class DashboardController {
    async index({ view, auth }) {
        
        const food = await Database.raw('SELECT SUM(price) AS total FROM receipts WHERE category = "Food"')
        const accommodation = await Database.raw('SELECT SUM(price) AS total FROM receipts WHERE category = "Accommodation"')
        const transport = await Database.raw('SELECT SUM(price) AS total FROM receipts WHERE category = "Transport"')
        const others = await Database.raw('SELECT SUM(price) AS total FROM receipts WHERE category = "Others"')
        
        return view.render('dashboard.index', {
            food: food[0],
            accommodation: accommodation[0],
            transport: transport[0],
            others: others[0],
        })
    }
}

module.exports = DashboardController
