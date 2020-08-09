'use strict'

/** @type {import('@adonisjs/lucid/src/Schema')} */
const Schema = use('Schema')

class ReceiptsSchema extends Schema {
  up () {
    this.create('receipts', (table) => {
      table.increments()
      table.string('name',15).notNullable()
      table.integer('user_id').unsigned().references('id').inTable('users').onDelete('CASCADE').onUpdate('CASCADE')
      table.date('date').notNullable()
      table.string('price', 10).notNullable()
      table.string('img_url', 255).notNullable()
      table.string('category', 20).notNullable()
      table.timestamps()
    })
  }

  down () {
    this.drop('receipts')
  }
}

module.exports = ReceiptsSchema
