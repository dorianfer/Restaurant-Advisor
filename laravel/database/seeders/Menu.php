<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;


class Menu extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('Menu')->insert([
            'name' => "Menu A5",
            'description' => "8 sushis, 4 makis, 4 calofornia rolls",
            'price' => 3.2,
            'restaurant_id' => 1,
        ]);

        DB::table('Menu')->insert([
            'name' => "Menu A6",
            'description' => "9 sushis, 4 makis, 4 calofornia rolls",
            'price' => 5.5,
            'restaurant_id' => 1,
        ]);

        DB::table('Menu')->insert([
            'name' => "Menu B5",
            'description' => "10 sushis, 4 makis, 4 calofornia rolls",
            'price' => 16.5,
            'restaurant_id' => 2,
        ]);
    }
}
