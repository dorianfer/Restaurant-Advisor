<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class testcontroller extends Controller
{
    public function index()
    {
        return response()->json(null, 200);
    }
}
