<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Menu extends Model
{
    use HasFactory;
    protected $table = 'Menu';
    public function Restaurant()
    {
        return $this->hasMany('app\Models\Restaurant', 'restaurant_id', 'id');
    }
}
