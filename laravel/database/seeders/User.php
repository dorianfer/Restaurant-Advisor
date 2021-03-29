<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;


class User extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('User')->insert([
            'login' => "didi",
            'password' => "dodo",
            'email' => "dori@outlook.fr",
            'firstname' => "Dorian",
            'name' => "Dorian",
            'age' => 19,
        ]);

        DB::table('User')->insert([
            'login' => "dodo",
            'password' => "dodo",
            'email' => "dorian@outlook.fr",
            'firstname' => "Dorian",
            'name' => "Dorian",
            'age' => 19,
        ]);

        DB::table('User')->insert([
            'login' => "fafa",
            'password' => "dodo",
            'email' => "dorian94@outlook.fr",
            'firstname' => "Dorian",
            'name' => "Dorian",
            'age' => 19,
        ]);
    }
}
