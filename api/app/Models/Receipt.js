'use strict'

/** @type {typeof import('@adonisjs/lucid/src/Lucid/Model')} */
const Model = use('Model')

class Receipt extends Model {
    static get hidden() {
        return ['updated_at', 'user_id', 'created_at']
    }
}

module.exports = Receipt
