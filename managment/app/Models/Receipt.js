'use strict'

/** @type {typeof import('@adonisjs/lucid/src/Lucid/Model')} */
const Model = use('Model')

class Receipt extends Model {
    static get hidden() {
        return ['id', 'user_id', 'key', 'created_at', 'updated_at']
    }
    user() {
        return this.belongsTo('App/Models/User')
    }
}

module.exports = Receipt
