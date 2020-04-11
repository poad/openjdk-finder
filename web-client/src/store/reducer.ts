import { reducerWithInitialState } from 'typescript-fsa-reducers'
import { State } from './openjdk/types'

export const initialState: State = {
  items: [],
  displayItems: [],
  page: {
    page: 0,
    rowsPerPage: 10
  },
  vendors: [],
  versions: [],
  architectures: [],
  types: [],
  bundles: [],
  os: [],
  fx: undefined,
  condition: {
    vendor: undefined,
    version: undefined,
    architecture: undefined,
    type: undefined,
    bundle: undefined,
    os: undefined,
    fx: undefined
  },
  loaded: false,
  error: false,
}

export const Reducer = reducerWithInitialState(initialState)
