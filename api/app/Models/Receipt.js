'use strict'

/** @type {typeof import('@adonisjs/lucid/src/Lucid/Model')} */
const Model = use('Model')
const moment = require("moment")

class Receipt extends Model {
    static get hidden() {
        return ['updated_at', 'user_id', 'created_at']
    }

    static get dates() {
        return super.dates.concat(["date"]);
    }

    static castDates(field, value) {
        if (field == "date") return value ? value.format("DD/MM/YYYY") : value;
        else return value ? value.format("DD/MM/YYYY hh:mm a") : value;
        // else used for created_at / updated_at
    }

    static formatDates(field, value) {
        if (field == "date")
            // format only certain fields
            return moment(value, "DD/MM/YYYY").format("YYYY-MM-DD");

        return super.formatDates(field, value);
    }
}

module.exports = Receipt
