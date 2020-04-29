import * as React from 'react'
import { withStyles, Theme, createStyles } from '@material-ui/core/styles';
import { State, OpenJDK, Page, Filter, Order, TableHeadLabel } from '../../store/openjdk/types'
import RestClient from '../RestClient'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TablePagination from '@material-ui/core/TablePagination';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import { Link } from '@material-ui/core';
import Tooltip from '@material-ui/core/Tooltip';
import IconButton from '@material-ui/core/IconButton';
import GetAppIcon from '@material-ui/icons/GetApp';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Backdrop from '@material-ui/core/Backdrop';
import Loader from 'react-loader';
import Dialog from '@material-ui/core/Dialog';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';

interface Props {
  items: Array<OpenJDK>,
  page: Page,
}

const StyledTableCell = withStyles((theme: Theme) => createStyles({
  root: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  }
})
)(TableCell);

const StyledSpinner = withStyles((_: Theme) => createStyles({
  root: {
    position: 'absolute',
    width: '40%',
    height: '40%',
    top: '50%',
    left: '50%',
    border: '1px solid #000'
  }
})
)(Loader);

const LoadingBackdrop = withStyles((theme: Theme) => createStyles({
  root: {
    zIndex: theme.zIndex.drawer + 1,
    color: '#fff',
  }
})
)(Backdrop);

class OpenJDKList extends React.Component<Props, State> {
  props: Props
  state: State
  client: RestClient

  constructor(props: Props) {
    super(props)

    this.props = props;
    this.state = {
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
      order: 'asc',
      orderBy: 'vendor',
      loaded: false,
      error: false,
      errorMessage: undefined,
    };
    this.client = new RestClient();
  }

  componentDidMount(): void {
    this.openLoading()
    this.fetchVendors()
    this.fetchVersions()
    this.fetchArchitectures()
    this.fetchTypes()
    this.fetchBundles()
    this.fetchOs()

    this.fetchList()
  }

  fetchList = (): void => {
    this.client.fetchList().then((items) => {
      this.filtering(items.map(item => item.fx != undefined ? item : Object.assign(item, {fx: false})));
      this.closeLoading();
    }).catch((e) => {
      this.openErrorDialog(e.toString());
    })
  }

  openLoading = (): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      order: this.state.order,
      orderBy: this.state.orderBy,
      loaded: false,
      error: false,
      errorMessage: undefined,
    })
  }

  closeLoading = (): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      order: this.state.order,
      orderBy: this.state.orderBy,
      loaded: true,
      error: false,
      errorMessage: this.state.errorMessage,
    })
  }

  openErrorDialog = (msg: string): void => {
    this.setState({
      items: this.state.items,
      displayItems: this.state.displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: this.state.condition,
      order: this.state.order,
      orderBy: this.state.orderBy,
      loaded: false,
      error: true,
      errorMessage: msg,
    })
  }

  fetchVendors = (): void => {
    this.client.fetchVedorList().then((vendors) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,

      })
    )
  }

  fetchVersions = (): void => {
    this.client.fetchVersionList().then((versions) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,

      })
    )
  }

  fetchArchitectures = (): void => {
    this.client.fetchArchitectureList().then((architectures) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,

      })
    )
  }

  fetchTypes = (): void => {
    this.client.fetchTypeList().then((types) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,

      })
    )
  }

  fetchBundles = (): void => {
    this.client.fetchBundleList().then((bundles) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,

      })
    )
  }

  fetchOs = (): void => {
    this.client.fetchOsList().then((os) =>
      this.setState({
        items: this.state.items,
        displayItems: this.state.displayItems,
        page: this.state.page,
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: os,
        fx: this.state.fx,
        condition: this.state.condition,
        order: this.state.order,
        orderBy: this.state.orderBy,
        loaded: this.state.loaded,
        error: this.state.error,
        errorMessage: this.state.errorMessage,
      })
    )
  }

  filtering = (items: Array<OpenJDK>, filter?: Filter): void => {
    var displayItems = items;
    if (filter) {
      displayItems = filter.vendor ? displayItems.filter(item => item.vendor == filter.vendor) : displayItems;
      displayItems = filter.version ? displayItems.filter(item => Number(item.majorVersion) == filter.version) : displayItems;
      displayItems = filter.architecture ? displayItems.filter(item => item.arch == filter.architecture) : displayItems;
      displayItems = filter.os ? displayItems.filter(item => item.os == filter.os) : displayItems;
      displayItems = filter.type ? displayItems.filter(item => item.type == filter.type) : displayItems;
      displayItems = filter.bundle ? displayItems.filter(item => item.bundle == filter.bundle) : displayItems;
      displayItems = filter.fx ? displayItems
        .map(item => item.fx ? item : Object.assign(item, {fx: false}))
        .map(item => {
          console.log(item.fx);
          return item;
        })
        .filter(item => item.fx == filter.fx) : displayItems;
    }

    this.setState({
      items: items,
      displayItems: displayItems,
      page: this.state.page,
      vendors: this.state.vendors,
      versions: this.state.versions,
      architectures: this.state.architectures,
      types: this.state.types,
      bundles: this.state.bundles,
      os: this.state.os,
      fx: this.state.fx,
      condition: filter ? filter : this.state.condition,
      order: this.state.order,
      orderBy: this.state.orderBy,
      loaded: this.state.loaded,
      error: this.state.error,
      errorMessage: this.state.errorMessage,
    })
  }

  headCells = [
    { id: 'vendor' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Vendor' },
    { id: 'version' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Version' },
    { id: 'arch' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Architecture' },
    { id: 'installationType' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Package Type' },
    { id: 'type' as TableHeadLabel, numeric: false, disablePadding: false, label: 'JVM implementation' },
    { id: 'bundle' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Type' },
    { id: 'os' as TableHeadLabel, numeric: false, disablePadding: false, label: 'OS' },
    { id: 'fx' as TableHeadLabel, numeric: false, disablePadding: false, label: 'OpenFX' },
    { id: 'timestamp' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Latest Update' },
    { id: 'url' as TableHeadLabel, numeric: false, disablePadding: false, label: 'Link' },
  ];

  handleRequestSort = (_: React.MouseEvent<unknown>, property:TableHeadLabel) => {
    const isDesc = this.state.orderBy === property && this.state.order === 'asc';
    this.setSortOrder(isDesc ? 'desc' : 'asc', property);
  };

  setSortOrder = (order: Order, orderBy: TableHeadLabel) => {
    this.setState(Object.assign(this.state, {
      order: order,
      orderBy: orderBy
    }));
  }


  public render(): JSX.Element {
    const setRowsPerPage = (newRowsPerPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: this.state.page.page,
          rowsPerPage: newRowsPerPage
        },
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        loaded: this.state.loaded,
        error: this.state.error,
      });
    };

    const setPage = (newPage: number) => {
      this.setState({
        items: this.state.items,
        page: {
          page: newPage,
          rowsPerPage: this.state.page.rowsPerPage
        },
        vendors: this.state.vendors,
        versions: this.state.versions,
        architectures: this.state.architectures,
        types: this.state.types,
        bundles: this.state.bundles,
        os: this.state.os,
        fx: this.state.fx,
        condition: this.state.condition,
        loaded: this.state.loaded,
        error: this.state.error,
      });
    };

    const handleChangePage = (_: unknown, newPage: number) => {
      setPage(newPage);
    };

    const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
      setRowsPerPage(Number.parseInt(event.target.value));
    };

    const onChangeState = (condition: Filter) => {
      this.filtering(this.state.items, condition)
    };

    const onVendorChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: event.target.value ? event.target.value as string : undefined,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onVersionChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: event.target.value ? event.target.value as number : undefined,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onArchitectureChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: event.target.value ? event.target.value as string : undefined,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onTypeChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: event.target.value ? event.target.value as string : undefined,
        bundle: this.state.condition.bundle,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onBundleChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: event.target.value ? event.target.value as string : undefined,
        os: this.state.condition.os,
        fx: this.state.condition.fx
      });
    };

    const onOsChange = (event: React.ChangeEvent<{ value: unknown }>) => {
      onChangeState({
        vendor: this.state.condition.vendor,
        version: this.state.condition.version,
        architecture: this.state.condition.architecture,
        type: this.state.condition.type,
        bundle: this.state.condition.bundle,
        os: event.target.value ? event.target.value as string : undefined,
        fx: this.state.condition.fx
      });
    };

    // const onFxChange = (event: React.ChangeEvent<{ value: unknown }>) => {
    //   onChangeState({
    //     vendor: this.state.condition.vendor,
    //     version: this.state.condition.version,
    //     architecture: this.state.condition.architecture,
    //     type: this.state.condition.type,
    //     bundle: this.state.condition.bundle,
    //     os: this.state.condition.os,
    //     fx: event.target.value ? event.target.value as boolean : undefined,
    //   });
    // };


    function descendingComparator<T>(a: T, b: T, orderBy: keyof T) {
      if (b[orderBy] < a[orderBy]) {
        return -1;
      }
      if (b[orderBy] > a[orderBy]) {
        return 1;
      }
      return 0;
    }


    const createSortHandler = (property: TableHeadLabel) => (event: React.MouseEvent<unknown>) => {
      this.handleRequestSort(event, property);
    }

    function getComparator<Key extends TableHeadLabel>(
      order: Order,
      orderBy: Key,
    ): (a: { [key in Key]: number | string }, b: { [key in Key]: number | string }) => number {
      return order === 'desc'
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
    }

    function stableSort<T>(array: T[], comparator: (a: T, b: T) => number) {
      const stabilizedThis = array.map((el, index) => [el, index] as [T, number]);
      stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) return order;
        return a[1] - b[1];
      });
      return stabilizedThis.map((el) => el[0]);
    }


    return (
      <React.Fragment>
        <LoadingBackdrop open={!this.state.loaded && !this.state.error} invisible={this.state.loaded || this.state.error}>
          <StyledSpinner loaded={this.state.loaded || this.state.error} />
        </LoadingBackdrop>
        <Dialog open={this.state.error}>
          <DialogContent>
            <DialogContentText id="alert-dialog-description">
              {this.state.errorMessage ? this.state.errorMessage : ""}
            </DialogContentText>
          </DialogContent>
        </Dialog>
        <Container fixed>
          <FormControl fullWidth>
            <Paper variant="outlined">
              <Grid
                container
                direction="row"
                spacing={2}
              >
                <Grid item xs={true}>
                  <TextField
                    id="vendor"
                    select
                    label="Vendor"
                    value={this.state.condition.vendor}
                    onChange={onVendorChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.vendors.map(vendor =>
                      <MenuItem key={vendor} value={vendor}>{vendor}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="version"
                    select
                    label="Major Version"
                    value={this.state.condition.version}
                    onChange={onVersionChange}
                    size={'medium'}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.versions.map(version =>
                      <MenuItem key={version} value={version}>{version}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="architecture"
                    select
                    label="Architecture"
                    value={this.state.condition.architecture}
                    onChange={onArchitectureChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.architectures.map(architecture =>
                      <MenuItem key={architecture} value={architecture}>{architecture}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="type"
                    select
                    label="JVM implementation"
                    value={this.state.condition.type}
                    onChange={onTypeChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.types.map(type =>
                      <MenuItem key={type} value={type}>{type}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="bundle"
                    select
                    label="bundle type"
                    value={this.state.condition.bundle}
                    onChange={onBundleChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.bundles.map(bundle =>
                      <MenuItem key={bundle} value={bundle}>{bundle}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                <Grid item xs={true}>
                  <TextField
                    id="os"
                    select
                    label="OS"
                    value={this.state.condition.os}
                    onChange={onOsChange}
                    fullWidth
                  >
                    <MenuItem>ALL</MenuItem>
                    {this.state.os.map(os =>
                      <MenuItem key={os} value={os}>{os}</MenuItem>
                    )}
                  </TextField>
                </Grid>
                {/* <Grid item xs={true}>
                  <TextField
                    id="fx"
                    select
                    label="OpenFX"
                    value={this.state.condition.fx}
                    onChange={onFxChange}
                    fullWidth
                  >
                    <MenuItem key={'ALL'}>ALL</MenuItem>
                    <MenuItem key={'true'} value='true'>with FX</MenuItem>
                    <MenuItem key={'false'} value='false'>without FX</MenuItem>
                  </TextField>
                </Grid> */}
              </Grid>
            </Paper>
          </FormControl>
        </Container>

        <Box component="span" m={1}>
          <Container fixed>
            <TableContainer>
              <Table size='small' stickyHeader>
                <TableHead>
                  <TableRow>
                    {this.headCells.map((headCell) => (
                      <StyledTableCell
                        key={headCell.id}
                        align={headCell.numeric ? 'right' : 'left'}
                        padding={headCell.disablePadding ? 'none' : 'default'}
                        sortDirection={this.state.orderBy === headCell.id ? this.state.order : false}
                      >
                        {headCell.id == 'url' ? headCell.label : (
                          <TableSortLabel
                            active={this.state.orderBy === headCell.id}
                            direction={this.state.orderBy === headCell.id ? this.state.order : 'asc'}
                            onClick={createSortHandler(headCell.id)}
                          >
                            {headCell.label}
                          </TableSortLabel>)
                        }
                      </StyledTableCell>
                    ))}
                  </TableRow>
                </TableHead>
                <TableBody>
                  {
                    stableSort(this.state.displayItems, getComparator(this.state.order, this.state.orderBy))
                    .slice(this.state.page.page * this.state.page.rowsPerPage, this.state.page.page * this.state.page.rowsPerPage + this.state.page.rowsPerPage)
                    .map((item) =>
                    <TableRow key={item.id}>
                      <TableCell>{item.vendor}</TableCell>
                      <TableCell>{item.version}</TableCell>
                      <TableCell>{item.arch}</TableCell>
                      <TableCell>{item.installationType}</TableCell>
                      <TableCell>{item.type}</TableCell>
                      <TableCell>{item.bundle}</TableCell>
                      <TableCell>{item.os}</TableCell>
                      <TableCell>{item.fx ? 'Y' : ''}</TableCell>
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
              count={this.state.displayItems.length}
              rowsPerPage={this.state.page.rowsPerPage}
              page={this.state.page.page}
              onChangePage={handleChangePage}
              onChangeRowsPerPage={handleChangeRowsPerPage}
            />
          </Container>
        </Box>
      </React.Fragment>
    );
  }
}

export default OpenJDKList;