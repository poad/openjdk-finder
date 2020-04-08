export const LIST = 'LIST'

export interface OpenJDK {
  id: string
  vendor: string,
  distribution: string,
  majorVersion: number,
  arch: string,
  version: string,
  installationType: string,
  extension: string | undefined,
  url: string,
  type: string,
  bundle: string,
  fx: boolean,
  os: string,
  timestamp: string | undefined,
}

export interface Filter {
  vendor: string | undefined,
  version: number | undefined,
  architecture: string | undefined,
  type: string | undefined,
  bundle: string | undefined,
  os: string | undefined,
  fx: boolean | undefined,
}

export type OpenJDKState = Array<OpenJDK>

export interface State {
  items: OpenJDKState,
  displayItems: OpenJDKState,
  page: Page,
  vendors: Array<string>,
  versions: Array<number>,
  architectures: Array<string>,
  types: Array<string>,
  bundles: Array<string>,
  os: Array<string>,
  fx: boolean | undefined,
  condition: Filter
}

interface ListAction {
  type: typeof LIST
}

export const GET = 'GET'
export const SET = 'SET'

export interface Page {
  page: number,
  rowsPerPage: number,
}

export interface State {
  page: Page
}

interface GetAction {
  type: typeof GET
}

interface SetAction {
  type: typeof SET
  payload: Page
}


export type ActionTypes =
  | ListAction
  | GetAction
  | SetAction

