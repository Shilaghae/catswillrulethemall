package cats.will.rule.them.all

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform