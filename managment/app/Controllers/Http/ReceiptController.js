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
      const receipts = await Receipt.query().where({user_id: auth.user.id}).fetch()
      return view.render('receipt.my', {
        receipts: receipts.toJSON()
      })
    }
    return response.redirect('/receipts')
  }

}

module.exports = ReceiptController
