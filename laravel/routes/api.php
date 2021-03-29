<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;
use App\Models\User;
use App\Models\Restaurant;
use App\Models\Menu;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
Route::post('register', function(Request $request) {
    $validator = Validator::make($request->all(), [
        'login' => 'required',
        'password' => 'required',
        'email' => 'required|email',
        'name' => 'required',
        'firstname' => 'required',
        'age' => 'required|integer',
    ]);
    
    if ($validator->fails()){
        return response()->json(null, 400);
    } else {
        $users = DB::table('User')->where('email', ($request->email))->first();
        if ($users != null) {
            return response()->json(null, 400);
        }
        $user = new User();
        $user->login = $request->login;
        $user->password = $request->password;
        $user->email = $request->email;
        $user->name = $request->name;
        $user->firstname = $request->firstname;
        $user->age = $request->age;
        $user->save();
        return response()->json(null, 201);
    }
});
Route::post('auth', function(Request $request) {
    $mail = $request->input('email');
    $psw = $request->input('password');
    if ($mail && $psw) {
        $user = DB::select('SELECT * FROM User WHERE email="'.$mail.'"');
        foreach ($user as $element) {
            if ($element->email == $mail && $element->password == $psw) {
                return response()->json(null, 200);
            }
        }
    }
    return response()->json(null, 400);
});
Route::get('users', function() {
    return User::all();
});
Route::get('restaurants', function() {
    return Restaurant::all();
});
Route::get('restaurants/{id}', function($id) {
    return Restaurant::find($id);
});
Route::post('restaurants', function(Request $request) {
    $validator = Validator::make($request->all(),[
        'name' => 'required',
        'description' => 'required',
        'grade' => 'required|integer',
        'localization' => 'required',
        'phone_number' => 'required',
        'website' => 'nullable',
        'hours' => 'required',
    ]);
    
    if ($validator->fails()){
        return response()->json(null, 400);
    } else {
        $restau = new Restaurant();
        $restau->name = $request->name;
        $restau->description = $request->description;
        $restau->grade = $request->grade;
        $restau->localization = $request->localization;
        $restau->phone_number = $request->phone_number;
        $restau->website = $request->website;
        $restau->hours = $request->hours;
        $restau->save();
        return response()->json(null, 201);
    }
});
Route::put('restaurant/{id}', function(Request $request, $id) {
    /*$restaurant = Restaurant::findOrFail($id);
    $restaurant->update($request->all());

    return $restaurant;*/
    $restau = DB::table('Restaurant')->where('id', $id)->first();
    if ($restau == null) {
        return response()->json(null, 400);
    }
    $validator = Validator::make($request->all(),[
        'name' => 'nullable',
        'description' => 'nullable',
        'grade' => 'nullable|integer',
        'localization' => 'nullable',
        'phone_number' => 'nullable',
        'website' => 'nullable',
        'hours' => 'nullable',
    ]);
    if ($validator->fails()){
        return response()->json(null, 400);
    }
    if ($request->name != null){
        Restaurant::where('id', $id)->update(['name' => $request->name]);
    };
    if ($request->description != null){
        Restaurant::where('id', $id)->update(['description' => $request->description]);
    };
    if ($request->grade != null){
        Restaurant::where('id', $id)->update(['grade' => $request->grade]);
    };
    if ($request->localization != null){
        Restaurant::where('id', $id)->update(['localization' => $request->localization]);
    };
    if ($request->phone_number != null){
        Restaurant::where('id', $id)->update(['phone_number' => $request->phone_number]);
    };
    if ($request->website != null){
        Restaurant::where('id', $id)->update(['website' => $request->website]);
    };
    if ($request->hours != null){
        Restaurant::where('id', $id)->update(['hours' => $request->hours]);
    };
    return response()->json(null, 200);
});
Route::delete('restaurant/{id}', function($id) {
    $restau = DB::table('Restaurant')->where('id', $id)->first();
    if ($restau == null) {
        return response()->json(null, 400);
    }
    Restaurant::find($id)->delete();

    return response()->json(null, 200);
});
Route::get('menus', function() {
    return Menu::all();
});
Route::get('menus/{id}', function($id) {
    return Menu::find($id);
});
Route::get('restaurants/{id}/menus', function($id) {
    return Menu::where('restaurant_id', $id)->get();
});
Route::post('restaurants/{id}/menus', function(Request $request, $id) {
    $validator = Validator::make($request->all(),[
        'name' => 'required',
        'description' => 'required',
        'price' => 'required|integer',
    ]);
    
    if ($validator->fails()){
        return response()->json(null, 400);
    } else {
        $menus = new Menu();
        $menus->name = $request->name;
        $menus->description = $request->description;
        $menus->price = $request->price;
        $menus->restaurant_id = $id;
        $menus->save();
        return response()->json(null, 201);
    }
});
Route::put('menus/{id}', function(Request $request, $id) {
    $menu = DB::table('Menu')->where('id', $id)->first();
    if ($menu == null) {
        return response()->json(null, 400);
    }
    $validator = Validator::make($request->all(),[
        'name' => 'nullable',
        'description' => 'nullable',
        'price' => 'nullable|integer',
    ]);
    if ($validator->fails()){
        return response()->json(null, 400);
    }
    if ($request->name != null){
        Menu::where('id', $id)->update(['name' => $request->name]);
    };
    if ($request->description != null){
        Menu::where('id', $id)->update(['description' => $request->description]);
    };
    if ($request->price != null){
        Menu::where('id', $id)->update(['price' => $request->price]);
    };
    return response()->json(null, 200);
});
Route::delete('menus/{id}', function($id) {
    $menu = DB::table('Menu')->where('id', $id)->first();
    if ($menu == null) {
        return response()->json(null, 400);
    }
    Menu::find($id)->delete();
    return response()->json(null, 200);
});
