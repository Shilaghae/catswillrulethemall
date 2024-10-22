package cats.will.rule.them.all.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.bindProvider
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.reflect.KClass

class ViewModelFactory(private val injector: DirectDI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return injector.viewModelInstance(modelClass)
    }
}

inline fun <reified T : ViewModel> DI.Builder.bindViewModel(noinline creator: DirectDI.() -> T) {
    return bindProvider<ViewModel>(T::class.qualifiedName, creator = creator)
}

@Suppress("UNCHECKED_CAST")
inline fun <T : ViewModel> DirectDI.viewModelInstance(modelClass: KClass<T>): T {
    return instance<ViewModel>(tag = modelClass.qualifiedName) as T
}

@Composable
inline fun <reified T : ViewModel> kodeinViewModel(): T {
    return viewModel<T>(factory = ViewModelFactory(AppDI.di.direct))
}