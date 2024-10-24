package cats.will.rule.them.all.di

import cats.will.rule.them.all.features.breeds.data.BreedPagingSource
import cats.will.rule.them.all.features.breeds.api.BreedRepository
import cats.will.rule.them.all.features.breeds.api.BreedService
import cats.will.rule.them.all.features.breeds.ui.BreedsViewModel
import cats.will.rule.them.all.network.KtorfitServiceCreator
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object AppDI {
    private val apiModule = DI.Module(name = "ApiModule") {
        bindSingleton<KtorfitServiceCreator> {
            KtorfitServiceCreator("https://api.thecatapi.com/")
        }
        bindSingleton<BreedService> {
            instance<KtorfitServiceCreator>().createBreedServices()
        }
    }

    private val breedRepository = DI.Module(name = "BreedsRepository") {
        bindProvider { BreedRepository(instance()) }
    }
    private val viewModelModule = DI.Module(name = "ViewModelModule") {
        bindViewModel { BreedsViewModel(instance()) }
    }
    private val breedSourcePaging = DI.Module(name = "BreedPagingSource") {
        bindProvider { BreedPagingSource(instance()) }
    }
    val di = DI {
        import(apiModule)
        import(breedRepository)
        import(viewModelModule)
        import(breedSourcePaging)
    }
}