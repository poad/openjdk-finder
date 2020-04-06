import { reducerWithInitialState } from 'typescript-fsa-reducers'
import { State } from './openjdk/types'

export const initialState: State = {
  items: [],
  page: {
    page: 0,
    rowsPerPage: 10
  }
}

export const Reducer = reducerWithInitialState(initialState)
