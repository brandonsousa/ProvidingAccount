'use strict'

/*
|--------------------------------------------------------------------------
| Routes
|--------------------------------------------------------------------------
|
| Http routes are entry points to your web application. You can create
| routes for different URLs and bind Controller actions to them.
|
| A complete guide on routing is available here.
| http://adonisjs.com/docs/4.1/routing
|
*/

/** @type {typeof import('@adonisjs/framework/src/Route/Manager')} */
const Route = use('Route')


Route.post('/', 'AuthController.login')
Route.get('/', 'AuthController.logout').middleware('auth')

Route.resource('user', 'UserController').only(['index']).middleware('auth') 
Route.resource('receipt', 'ReceiptController').only(['index', 'store', 'show', 'destroy']).middleware('auth') 