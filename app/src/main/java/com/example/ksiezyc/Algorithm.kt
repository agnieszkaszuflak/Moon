package com.example.ksiezyc

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*


class Algorithm {
    companion object {
        private var day: Int
        private var month: Int
        private var year: Int
        private var now = 0
        private var full = 0
        private var year1 = 2020
        private var number = 0

        fun getYear(): Int {
            return year1
        }

        init {
            day = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
            month = Calendar.getInstance()[Calendar.MONTH]
            year = Calendar.getInstance()[Calendar.YEAR]
        }

        fun plusYear() {
            this.year1 += 1
        }

        fun minusYear() {
            this.year1 -= 1
        }

        fun Trig2(year: Int, month: Int, day: Int): Int {
            val n =
                Math.floor(12.37 * (year - 1900 + (1.0 * month - 0.5) / 12.0))
            val RAD = 3.14159265 / 180.0
            val t = n / 1236.85
            val t2 = t * t
            val `as` = 359.2242 + 29.105356 * n
            val am = 306.0253 + 385.816918 * n + 0.010730 * t2
            var xtra =
                0.75933 + 1.53058868 * n + (1.178e-4 - 1.55e-7 * t) * t2
            xtra += (0.1734 - 3.93e-4 * t) * Math.sin(RAD * `as`) - 0.4068 * Math.sin(
                RAD * am
            )
            val i =
                if (xtra > 0.0) Math.floor(xtra) else Math.ceil(xtra - 1.0)
            val j1 = julday(year, month, day)
            val jd = 2415020 + 28 * n + i
            return ((j1 - jd + 30) % 30).toInt()
        }

        fun Simple(year: Int, month: Int, day: Int): Int {
            var lp = 2551443
            var now = Date(year, month - 1, day, 20, 35, 0)
            var new_moon = Date(1970, 0, 7, 20, 35, 0)
            var phase = (now.time - new_moon.time) / 1000 % lp
            return Math.floor(phase / (24 * 3600).toDouble()).toInt() + 1
        }

        fun Conway(year: Int, month: Int, day: Int): Int {
            var r = year % 100.toDouble()
            r %= 19.0
            if (r > 9) {
                r -= 19.0
            }
            r = r * 11 % 30 + month + day
            if (month < 3) {
                r += 2.0
            }
            if (year < 2000) {
                r -= 4
            } else {
                r -= 8.3
            }

            r = Math.floor(r + 0.5) % 30
            return if (r < 0) (r + 30).toInt() else r.toInt()
        }

        fun julday(year: Int, month: Int, day: Int): Int {
            var year = year
            if (year < 0) {
                year++
            }
            var jy = year
            var jm = month + 1
            if (month <= 2) {
                jy--
                jm += 12
            }
            var jul =
                (Math.floor(365.25 * jy) + Math.floor(30.6001 * jm) + day + 1720995).toInt()
            if (day + 31 * (month + 12 * year) >= 15 + 31 * (10 + 12 * 1582)) {
                val ja = Math.floor(0.01 * jy).toInt()
                jul = (jul + 2 - ja + Math.floor(0.25 * ja)).toInt()
            }
            return jul
        }

        fun GetFrac(fr: Double): Double {
            return fr - Math.floor(fr)
        }

        fun Trig1(year: Int, month: Int, day: Int): Int {
            val thisJD = julday(year, month, day)
            val degToRad = 3.14159265 / 180
            val K0: Double
            val T: Double
            val T2: Double
            val T3: Double
            val J0: Double
            val F0: Double
            val M0: Double
            val M1: Double
            val B1: Double
            var oldJ: Double = 0.0
            K0 = Math.floor((year - 1900) * 12.3685)
            T = (year - 1899.5) / 100
            T2 = T * T
            T3 = T * T * T
            J0 = 2415020 + 29 * K0
            F0 = 0.0001178 * T2 - 0.000000155 * T3 + (0.75933 + 0.53058868 * K0) - (0.000837 * T + 0.000335 * T2)
            M0 = 360 * GetFrac(K0 * 0.08084821133) + 359.2242 - 0.0000333 * T2 - 0.00000347 * T3
            M1 = 360 * GetFrac(K0 * 0.07171366128) + 306.0253 + 0.0107306 * T2 + 0.00001236 * T3
            B1 = 360 * GetFrac(K0 * 0.08519585128) + 21.2964 - 0.0016528 * T2 - 0.00000239 * T3
            var phase = 0.0
            var jday = 0.0
            while (jday < thisJD) {
                var F = F0 + 1.530588 * phase
                val M5 = (M0 + phase * 29.10535608) * degToRad
                val M6 = (M1 + phase * 385.81691806) * degToRad
                val B6 = (B1 + phase * 390.67050646) * degToRad
                F -= 0.4068 * Math.sin(M6) + (0.1734 - 0.000393 * T) * Math.sin( M5 )
                F += 0.0161 * Math.sin(2 * M6) + 0.0104 * Math.sin(2 * B6)
                F -= 0.0074 * Math.sin(M5 - M6) - 0.0051 * Math.sin(M5 + M6)
                F += 0.0021 * Math.sin(2 * M5) + 0.0010 * Math.sin(2 * B6 - M6)
                F += 0.5 / 1440
                oldJ = jday
                jday = J0 + 28 * phase + Math.floor(F)
                phase++
            }
            return ((thisJD - oldJ) % 30).toInt()
        }

        fun getDateN(algo : Int = 3 ): String {
            if (algo == 1) {
                number = Simple(year, month, day)
            }
            if (algo == 2) {
                number = Conway(year, month, day)
            }
            if (algo == 3) {
                number = Trig1(year, month, day)
            }
            if (algo == 4) {
                number = Trig2(year, month, day)
            }
            val check = przestepny(year)
            now = day - number
            return check2(now, check, day, month, year)
        }

        fun getFull(algo: Int = 3): String {
            var string = ""
            var day2 = 1
            var month2 = 1
            for (i in 1..13) {
                if (algo == 1) {
                    number = Simple(year1, month2, day2)
                }
                if (algo == 2) {
                    number = Conway(year1, month2, day2)
                }
                if (algo == 3) {
                    number = Trig1(year1, month2, day2)
                }
                if (algo == 4) {
                    number = Trig2(year1, month2, day2)
                }
                var check = przestepny(year1)
                full = if (number < 15) {
                    day2 + 15 - number
                } else {
                    day2 + 45 - number
                }
                if (i == 13) {
                    val s = check(full, check, day2, month2, year1)
                    day2 = check_day(full, check, day2, month2, year1)
                    month2 = check_month(full, check, day2, month2, year1)
                    if (month2 == 1) {
                        return string
                    } else {
                        return string + "\n" + s
                    }
                }
                string = string + "\n" + check(full, check, day2, month2, year1)
                day2 = check_day(full, check, day2, month2, year1) + 1
                month2 = check_month(full, check, day2, month2, year1)
            }
            return string
        }

        fun getDateF(algo: Int = 3): String {
            if (algo == 1) {
                number = Simple(year, month, day)
            }
            if (algo == 2) {
                number = Conway(year, month, day)
            }
            if (algo == 3) {
                number = Trig1(year, month, day)
            }
            if (algo == 4) {
                number = Trig2(year, month, day)
            }
            val check = przestepny(year)
            full = if (number <= 15) {
                day + 15 - number
            } else {
                day + 45 - number
            }
            return check(full, check, day, month, year)
        }

        fun getPercent(algo: Int = 3): String {
            if (algo == 1) {
                number = Simple(year, month, day)
            }
            if (algo == 2) {
                number = Conway(year, month, day)
            }
            if (algo == 3) {
                number = Trig1(year, month, day)
            }
            if (algo == 4) {
                number = Trig2(year, month, day)
            }
            return ((number+1) * 100 / 30).toString() + '%'
        }

        fun getNumber(which: String = "n", algo: Int = 3): Int {
            if (algo == 1) {
                number = Simple(year, month, day)
            }
            if (algo == 2) {
                number = Conway(year, month, day)
            }
            if (algo == 3) {
                number = Trig1(year, month, day)
            }
            if (algo == 4) {
                number = Trig2(year, month, day)
            }
            return name("$which$number.jpg")
        }

        fun name(jpg: String): Int {
            when (jpg) {
                "n0.jpg" -> return R.drawable.n0
                "n1.jpg" -> return R.drawable.n1
                "n2.jpg" -> return R.drawable.n2
                "n3.jpg" -> return R.drawable.n3
                "n4.jpg" -> return R.drawable.n4
                "n5.jpg" -> return R.drawable.n5
                "n6.jpg" -> return R.drawable.n6
                "n7.jpg" -> return R.drawable.n7
                "n8.jpg" -> return R.drawable.n8
                "n9.jpg" -> return R.drawable.n9
                "n10.jpg" -> return R.drawable.n10
                "n11.jpg" -> return R.drawable.n11
                "n12.jpg" -> return R.drawable.n12
                "n13.jpg" -> return R.drawable.n13
                "n14.jpg" -> return R.drawable.n14
                "n15.jpg" -> return R.drawable.n15
                "n16.jpg" -> return R.drawable.n16
                "n17.jpg" -> return R.drawable.n17
                "n18.jpg" -> return R.drawable.n18
                "n19.jpg" -> return R.drawable.n19
                "n20.jpg" -> return R.drawable.n20
                "n21.jpg" -> return R.drawable.n21
                "n22.jpg" -> return R.drawable.n22
                "n23.jpg" -> return R.drawable.n23
                "n24.jpg" -> return R.drawable.n24
                "n25.jpg" -> return R.drawable.n25
                "n26.jpg" -> return R.drawable.n26
                "n27.jpg" -> return R.drawable.n27
                "n28.jpg" -> return R.drawable.n28
                "n29.jpg" -> return R.drawable.n29
                "s0.jpg" -> return R.drawable.s0
                "s1.jpg" -> return R.drawable.s1
                "s2.jpg" -> return R.drawable.s2
                "s3.jpg" -> return R.drawable.s3
                "s4.jpg" -> return R.drawable.s4
                "s5.jpg" -> return R.drawable.s5
                "s6.jpg" -> return R.drawable.s6
                "s7.jpg" -> return R.drawable.s7
                "s8.jpg" -> return R.drawable.s8
                "s9.jpg" -> return R.drawable.s9
                "s10.jpg" -> return R.drawable.s10
                "s11.jpg" -> return R.drawable.s11
                "s12.jpg" -> return R.drawable.s12
                "s13.jpg" -> return R.drawable.s13
                "s14.jpg" -> return R.drawable.s14
                "s15.jpg" -> return R.drawable.s15
                "s16.jpg" -> return R.drawable.s16
                "s17.jpg" -> return R.drawable.s17
                "s18.jpg" -> return R.drawable.s18
                "s19.jpg" -> return R.drawable.s19
                "s20.jpg" -> return R.drawable.s20
                "s21.jpg" -> return R.drawable.s21
                "s22.jpg" -> return R.drawable.s22
                "s23.jpg" -> return R.drawable.s23
                "s24.jpg" -> return R.drawable.s24
                "s25.jpg" -> return R.drawable.s25
                "s26.jpg" -> return R.drawable.s26
                "s27.jpg" -> return R.drawable.s27
                "s28.jpg" -> return R.drawable.s28
                "s29.jpg" -> return R.drawable.s29

                else -> return 0
            }
        }

        private fun przestepny(year: Int): Boolean {
            return year % 4 == 0 && year % 100 != 0 || year % 400 == 0
        }

        private fun check(number: Int, check: Boolean, day: Int, month: Int, year: Int): String {
            var day1 = day
            var month1 = month
            var year1 = year
            if (number > 28) {
                if (month1 == 2) {
                    if (number > 29 && check) {
                        day1 = number - 29
                        month1 += 1
                        return "$day1.$month1.$year1"
                    }
                    if (number > 28 && !check) {
                        day1 = number - 28
                        month1 += 1
                        return "$day1.$month1.$year1"
                    }
                }
                if (number > 30) {
                    if (month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) {
                        day1 = number - 30
                        month1 += 1
                        return "$day1.$month1.$year1"
                    }
                }
                if (number > 31) {
                    if (month1 == 1 || month1 == 3 || month1 == 5 || month1 == 7 || month1 == 8 || month1 == 10) {
                        day1 = number - 31
                        month1 += 1
                        return "$day1.$month1.$year1"
                    }
                    if (month1 == 12) {
                        day1 = number - 31
                        month1 = 1
                        year1 += 1
                        return "$day1.$month1.$year1"
                    }
                }
            }
            day1 = number
            return "$day1.$month1.$year1"
        }

        private fun check_month(number: Int, check: Boolean, day: Int, month: Int, year: Int): Int {
            var month1 = month
            if (number > 28) {
                if (month1 == 2) {
                    if (number > 29 && check) {
                        month1 += 1
                        return month1
                    }
                    if (number > 28 && !check) {
                        month1 += 1
                        return month1
                    }
                }
                if (number > 30) {
                    if (month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) {
                        month1 += 1
                        return month1
                    }
                }
                if (number > 31) {
                    if (month1 == 1 || month1 == 3 || month1 == 5 || month1 == 7 || month1 == 8 || month1 == 10) {
                        month1 += 1
                        return month1
                    }
                    if (month1 == 12) {
                        month1 = 1
                        return month1
                    }
                }
            }
            return month1
        }

        private fun check_day(number: Int, check: Boolean, day: Int, month: Int, year: Int): Int {
            var day1 = day
            var month1 = month
            if (number > 28) {
                if (month1 == 2) {
                    if (number > 29 && check) {
                        day1 = number - 29
                        return day1
                    }
                    if (number > 28 && !check) {
                        day1 = number - 28
                        return day1
                    }
                }
                if (number > 30) {
                    if (month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) {
                        day1 = number - 30
                        return day1
                    }
                }
                if (number > 31) {
                    if (month1 == 1 || month1 == 3 || month1 == 5 || month1 == 7 || month1 == 8 || month1 == 10) {
                        day1 = number - 31
                        return day1
                    }
                    if (month1 == 12) {
                        day1 = number - 31
                        return day1
                    }
                }
            }
            day1 = number
            return day1
        }


        private fun check2(number: Int, check: Boolean, day: Int, month: Int, year: Int): String {
            var day1 = day
            var month1 = month
            var year1 = year
            if (number < 1) {
                if (month1 == 2 || month1 == 4 || month1 == 6 || month1 == 11 || month1 == 9 || month1 == 8) {
                    day1 = 31 + number
                    month1 -= 1
                    return "$day1.$month1.$year1"

                }
                if (month1 == 3) {
                    if (check) {
                        day1 = 29 + number
                        month1 -= 1
                        return "$day1.$month1.$year1"
                    }
                    if (!check) {
                        day1 = 28 + number
                        month1 -= 1
                        return "$day1.$month1.$year1"
                    }
                }


                if (month1 == 5 || month1 == 7 || month1 == 10 || month1 == 12) {
                    day1 = number + 30
                    month1 -= 1
                    return "$day1.$month1.$year1"
                }
                if (month1 == 1) {
                    day1 = 31 + number
                    month1 -= 1
                    year1 -= 1
                    return "$day1.$month1.$year1"
                }

            }
            day1 = number
            return "$day1.$month1.$year1"
        }
    }
}



