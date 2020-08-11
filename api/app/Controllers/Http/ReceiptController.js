'use strict'

/** @typedef {import('@adonisjs/framework/src/Request')} Request */
/** @typedef {import('@adonisjs/framework/src/Response')} Response */
/** @typedef {import('@adonisjs/framework/src/View')} View */

const Receipt = use('App/Models/Receipt')
const Database = use('Database')
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
  async index({ response, auth }) {
    const receipts = await Receipt.findBy('user_id', auth.user.id)
    if (receipts) {
      return response.status(200).send(receipts)
    }
    return response.status(400).send(null)
  }

  /**
   * Create/save a new receipt.
   * POST receipts
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async store({ request, response, auth }) {
    const data = request.all()

    const receipt = await Receipt.create({
      'user_id': auth.user.id,
      ...data
    })

    if (receipt) {
      return response.status(201).send(receipt)
    }
    return response.status(400).send(null)
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
  async show({ params, response, auth }) {
    const receipt = await Database.raw(
      'SELECT * FROM receipts WHERE id = ? AND user_id = ?',
      [params.id, auth.user.id]
    )
    return response.status(200).send(receipt[0])
  }

  /**
   * Delete a receipt with id.
   * DELETE receipts/:id
   *
   * @param {object} ctx
   * @param {Request} ctx.request
   * @param {Response} ctx.response
   */
  async destroy({ params, request, response, auth }) {

  }
}

module.exports = ReceiptController
