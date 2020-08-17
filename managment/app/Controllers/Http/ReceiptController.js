'use strict'

/** @typedef {import('@adonisjs/framework/src/Request')} Request */
/** @typedef {import('@adonisjs/framework/src/Response')} Response */
/** @typedef {import('@adonisjs/framework/src/View')} View */

const Database = use('Database')

const Receipt = use('App/Models/Receipt')
class ReceiptController {
  /**
   * Show a list of all receipts.
   * GET receipts
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async index({ view }) {

    return view.render('receipt.index', {
      all: null
    })
  }

  /**
   * Display a single receipt.
   * GET receipts/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   * @param {View} ctx.view
   */
  async show({ params, response, view, auth }) {
    if (auth.user.id == params.id) {
      const receipts = await Receipt.query().where({ user_id: auth.user.id }).fetch()
      return view.render('receipt.my', {
        receipts: receipts.toJSON()
      })
    }
    return response.redirect('/receipts')
  }

  async getByUser({ request, response }) {
    const { search } = request.all()
    const receipts = await Database.raw('SELECT u.code, u.username, r.* ' +
      'FROM users u ' +
      'INNER JOIN receipts r ON r.user_id = u.id ' +
      'WHERE u.code = ? OR u.email = ?', [search, search])

    if (receipts[0].length == 0) {
      return response.status(400).send({
        data: 'No content to user'
      })
    }

    return response.status(200).send({
      data: receipts[0]
    })
  }

}

module.exports = ReceiptController
