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
    const data = request.except(['key'])

    const receipt = await Receipt.create({
      'user_id': auth.user.id,
      'key': Math.random().toString(36).slice(-10),
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
      'SELECT * FROM recipts WHERE id = ? AND user_id = ?',
      [params.id, auth.user.id]
    )
    if (receipt[0].length == 0) {
      return response.status(200).send({
        receipt: receipt[0]
      })
    }

    return response.status(400).send(null)
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
