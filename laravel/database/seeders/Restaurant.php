<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class Restaurant extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('Restaurant')->insert([
            'name' => "MacDonald's",
            'description' => "Classic, long-running fast-food chain known for its burgers, fries & shakes.",
            'grade' => 3.2,
            'localization' => "Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur Seine",
            'phone_number' => "01 49 60 62 60",
            'website' => "macdo.fr",
            'hours' => "Monday-Saturday 9AM-9PM, Sunday Closed",
        ]);

        DB::table('Restaurant')->insert([
            'name' => "StarBuck",
            'description' => "Coffe.",
            'grade' => 2.9,
            'localization' => "Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur Seine",
            'phone_number' => "01 49 60 62 60",
            'website' => "starbuck.eu",
            'hours' => "Monday-Saturday 9AM-9PM, Sunday Closed",
        ]);

        DB::table('Restaurant')->insert([
            'name' => "La Comedia",
            'description' => "Kebabs.",
            'grade' => 4.9,
            'localization' => "Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur Seine",
            'phone_number' => "01 49 60 62 60",
            'website' => "comedia.com",
            'hours' => "Monday-Saturday 9AM-9PM, Sunday Closed",
        ]);
    }
}
