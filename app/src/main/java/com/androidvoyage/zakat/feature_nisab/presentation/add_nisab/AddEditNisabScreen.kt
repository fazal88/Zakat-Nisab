package com.androidvoyage.zakat.feature_nisab.presentation.add_nisab

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androidvoyage.zakat.feature_nisab.presentation.add_nisab.components.TransparentHintTextField
import com.androidvoyage.zakat.feature_nisab.presentation.util.Features
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditNisabScreen(
    navController: NavController,
    nisabId: Long,
    nisabType: String,
    viewModel: AddEditNisabViewModel = hiltViewModel()
) {
    val titleState = viewModel.nisabTitle.value
    val amountState = viewModel.nisabAmount.value
    val contentState = viewModel.nisabContent.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNisabViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNisabViewModel.UiEvent.SaveNisab -> {
                    navController.navigateUp()
                }
            }
        }
    }


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetNisabType(viewModel.nisabType.value) {
                viewModel.setNisabType(it)
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }, sheetPeekHeight = 0.dp
    ) {
        BoxWithConstraints(modifier = Modifier
            .fillMaxSize()
            .clickable {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        }
                ) {
                    Text(
                        text = if (viewModel.nisabType.value.isEmpty()) "Select Nisab Type" else viewModel.nisabType.value,
                        style = MaterialTheme.typography.h5,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                            .padding(16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Nisab Type",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterEnd)
                            .offset(-16.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = amountState.text,
                    hint = amountState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredAmount(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeAmountFocus(it))
                    },
                    isHintVisible = amountState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ).padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ).padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNisabEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNisabEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 0.5.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        ).padding(16.dp)
                )
            }
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNisabEvent.SaveNisab)
                },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(-16.dp, -16.dp)
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save nisab")
            }
        }
    }
}

@Composable
fun BottomSheetNisabType(typeSelected: String, callbackFun: (nisabType: String) -> Unit) {
    Column() {
        for (nisabType in Features.prefTitleList) {
            NisabDropdownItem(nisabType = nisabType, typeSelected, callbackFun)
        }
    }

}

@Composable
fun NisabDropdownItem(
    nisabType: String,
    selected: String,
    callbackFun: (nisabType: String) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
            .clickable {
                callbackFun(nisabType)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (title, icon) = createRefs()
            Icon(
                painter = painterResource(id = Features.getIcon(nisabType)),
                contentDescription = nisabType,
                tint = Color.Unspecified,
                modifier = Modifier
                    .constrainAs(icon) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
            )
            Text(
                text = nisabType,
                style = MaterialTheme.typography.h2,
                color = Color.Black,
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )
        }
    }
}
