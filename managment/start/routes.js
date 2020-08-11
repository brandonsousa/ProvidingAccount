'use strict'

/*
|--------------------------------------------------------------------------
| Routes
|--------------------------------------------------------------------------
|
| Http routes are entry points to your web application. You can create
| routes for different URL's and bind Controller actions to them.
|
| A complete guide on routing is available here.
| http://adonisjs.com/docs/4.1/routing
|
*/

/** @type {typeof import('@adonisjs/framework/src/Route/Manager')} */
const Route = use('Route')

Route.get('/', 'AuthController.index')
Route.post('/login', 'AuthController.login')
Route.get('/logout', 'AuthController.logout')

Route.resource('/dashboard', 'DashboardController').middleware('auth')
Route.resource('/user', 'UserController').middleware('auth')
Route.resource('/receipts', 'ReceiptController').middleware('auth')
Route.resource('/profile', 'ProfileController').middleware('auth')
Route.resource('/report', 'ReportController')
