package cats.will.rule.them.all.features.breeds.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import cats.will.rule.them.all.features.breeds.api.BreedRepository
import io.github.aakira.napier.Napier

private val TAG = BreedPagingSource::class.simpleName

class BreedPagingSource(
    private val repository: BreedRepository
) : PagingSource<Int, Breed>() {

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        val currentPage = params.key
        val size = params.loadSize
        return try {
            val response = repository.getBreeds(limit = size, page = currentPage)
            PagingSourceLoadResultPage(
                data = response.breeds,
                prevKey = null,
                nextKey = response.pagination.nextPage

            )
        } catch (e: Throwable) {
            Napier.e("Error loading page: $currentPage", e, TAG)
            PagingSourceLoadResultError(e)
        }
    }
}