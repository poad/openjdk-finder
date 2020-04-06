import * as React from 'react'
import { State, OpenJDK, Page } from '../../store/openjdk/types'
import RestClient from '../RestClient'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';
import { Link } from '@material-ui/core';
import Tooltip from '@material-ui/core/Tooltip';
import IconButton from '@material-ui/core/IconButton';
import GetAppIcon from '@material-ui/icons/GetApp';
import { Select } from '@material-ui/core';

interface Props {
  items: Array<OpenJDK>,
  page: Page
}

export default class OpenJDKList extends React.Component<Props, State> {
  props: Props
  state: State
  client: RestClient

  constructor(props: Props) {
    super(props)
    this.props = props
    this.state = {
      items: [],
      page: {
        page: 0,
        rowsPerPage: 10
      }
    }
    this.client = new RestClient()
  }

  componentDidMount(): void {
    this.fetchList()
  }

  fetchList = (): void => {
    this.client.fetchList().then((items) =>
      this.setState({
        items: items,
        page: this.state.page
      })
    )
  }

  headCells = [
    { id: 'vendor', numeric: false, disablePadding: true, label: 'Vendor' },
    { id: 'version', numeric: false, disablePadding: false, label: 'Version' },
    { id: 'architecture', numeric: false, disablePadding: false, label: 'Architecture' },
    { id: 'installationType', numeric: false, disablePadding: false, label: 'Package Type' },
    { id: 'type', numeric: false, disablePadding: false, label: 'JVM implementation' },
    { id: 'bundle', numeric: false, disablePadding: false, label: 'Type' },
    { id: 'os', numeric: false, disablePadding: false, label: 'OS' },
    { id: 'timestamp', numeric: false, disablePadding: false, label: 'Latest Update' },
    { id: 'download', numeric: false, disablePadding: false, label: 'Link' },
  ];

  public render(): JSX.Element {

    const setRowsPerPage = (newRowsPerPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: this.state.page.page,
          rowsPerPage: newRowsPerPage
        }
      });
    };

    const setPage = (newPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: newPage,
          rowsPerPage: this.state.page.rowsPerPage
        }
      });
    };

    const handleChangePage = (_: unknown, newPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: newPage,
          rowsPerPage: this.state.page.rowsPerPage
        }
      });
    };

    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
      setRowsPerPage(+event.target.value);
      setPage(0);
    };

    return (
      <div>
        <div>
          <Select>

          </Select>
          <Select>

          </Select>
          <Select>

          </Select>
          <Select>

          </Select>
          <Select>

          </Select>
          <Select>

          </Select>
          <Select>

          </Select>
        </div>
        <TableContainer>
          <Table size='small'>
            <TableHead>
              <TableRow>
                {this.headCells.map((headCell) => (
                  <TableCell
                    key={headCell.id}
                    align={headCell.numeric ? 'right' : 'left'}
                    padding={headCell.disablePadding ? 'none' : 'default'}
                    sortDirection={false}
                  >
                    {headCell.label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.items.slice(this.state.page.page * this.state.page.rowsPerPage, this.state.page.page * this.state.page.rowsPerPage + this.state.page.rowsPerPage).map((item) =>
                <TableRow key={item.id}>
                  <TableCell>{item.vendor}</TableCell>
                  <TableCell>{item.version}</TableCell>
                  <TableCell>{item.arch}</TableCell>
                  <TableCell>{item.installationType}</TableCell>
                  <TableCell>{item.type}</TableCell>
                  <TableCell>{item.bundle}</TableCell>
                  <TableCell>{item.os}</TableCell>
                  <TableCell>{item.fx}</TableCell>
                  <TableCell>{item.timestamp}</TableCell>
                  <TableCell>
                    <Link href={item.url}>
                      <Tooltip title={item.url}>
                        <IconButton aria-label="download">
                          <GetAppIcon />
                        </IconButton>
                      </Tooltip>
                    </Link>
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[10, 25, 100]}
          component="div"
          count={this.state.items.length}
          rowsPerPage={this.state.page.rowsPerPage}
          page={this.state.page.page}
          onChangePage={handleChangePage}
          onChangeRowsPerPage={handleChangeRowsPerPage}
        />
      </div>
    );
  }
}
